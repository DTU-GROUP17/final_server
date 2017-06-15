package weighting;

import app.App;
import models.db.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WeightingSession {

	private WeightManager controller;
	private Weight weight;
	private ProductBatch batch;

	public WeightingSession(WeightManager controller, Weight weight) {
		this.controller = controller;
		this.weight = weight;
	}

	private User getVerifiedUser() {
		while (true) {
			Integer	id = Integer.parseInt(
					this.controller.prompt("User ID:", "0")
			);
			User user;
			try (Session session = App.openSession()) {
				user = session.find(User.class, id);
			}
			if (user==null){
				this.controller.confirmedMessage("No user with that id");
				continue;
			}
			if (!user.hasRole("Lab_Technician")) {
				this.controller.confirmedMessage("User not allowed");
				continue;
			}
			this.controller.confirmedMessage(
				String.format(
					"Logged in as %s",
					user.getName()
				)
			);
			return user;
		}
	}

	private ProductBatch getVerifiedBatch() {
		while (true) {
			Integer id = Integer.parseInt(
				this.controller.prompt("Batch ID:", "0")
			);
			ProductBatch batch;
			try (Session session = App.openSession()) {
				batch = session.find(ProductBatch.class, id);
			}
			if (batch == null) {
				this.controller.confirmedMessage("No batch with that id");
				continue;
			}
			if (
				batch.getStatus() != ProductBatch.Status.QUEUED
				&& batch.getStatus() != ProductBatch.Status.ACTIVE
			) {
				this.controller.confirmedMessage(
					String.format(
						"Batch is currently %s",
						batch.getStatus().toString()
					)
				);
				continue;
			}
			this.controller.confirmedMessage(
				String.format(
					"Operating on batch for %s",
					batch.getRecipe().getName()
				)
			);
			return batch;
		}
	}

	private Material getVerifiedMaterial() {
		while (true) {
			Integer id = Integer.parseInt(
				this.controller.prompt("Material ID:", "0")
			);
			Material material;
			try (Session session = App.openSession()) {
				material = session.find(Material.class, id);
			}
			if (material == null) {
				this.controller.confirmedMessage("No material with that id");
				continue;
			}
			this.controller.confirmedMessage(
				String.format(
					"Weighting material of %s",
					material.getComponent().getName()
				)
			);
			return material;
		}
	}

	private void abort() {
		if (this.batch == null){
			return;
		}
		this.setBatchStatus(ProductBatch.Status.QUEUED);
	}

	private void complete() {
		this.setBatchStatus(ProductBatch.Status.COMPLETE);
	}

	private void begin() {
		this.setBatchStatus(ProductBatch.Status.ACTIVE);
	}

	private void setBatchStatus(ProductBatch.Status status) {
		try(Session session = App.openSession()) {
			Transaction transaction = session.beginTransaction();
			this.batch.setStatus(status);
			session.update(this.batch);
			transaction.commit();
		}


	}

	private Weighing weigh() {
		Weighing weighing = new Weighing();
		return weighing.setMaterial(
			this.getVerifiedMaterial()
		)
			.setAmount(
				this.controller.getStapleLoad()
			)
				.setWeighedAt(
					new Timestamp(System.currentTimeMillis())
				);
	}

	private double getIngredientSaturation(List<Weighing> weighings, Component component) {
		return weighings.stream()
			.filter(
				weighing -> weighing.getMaterial().getComponent().equals(component)
			)
			.mapToDouble(Weighing::getAmount)
			.sum();
	}

	private boolean recipeCompleted(List<Weighing> weighings, List<Ingredient> ingredients) {
		for (Ingredient ingredient : ingredients) {
			if (
				this.getIngredientSaturation(
					weighings,
					ingredient.getComponent()
				)
				<
				ingredient.getLowerLimit()
			) {
				return false;
			}
		}
		return true;
	}

	private void perform() {
		User user = this.getVerifiedUser();
		this.batch = this.getVerifiedBatch();
		this.begin();
		this.controller.tare();
		try (Session session = App.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Weighing> query = builder.createQuery(Weighing.class);
			Root<Weighing> root = query.from(Weighing.class);
			query.select(root).where(
				builder.equal(root.get("productBatch"), this.batch)
			);
			List<Weighing> weightings = session.createQuery(query).getResultList();
			List<Ingredient> ingredients = this.batch.getRecipe().getIngredients();
			while (!this.recipeCompleted(weightings, ingredients)) {
				Weighing weighing = this.weigh();
				Optional<Ingredient> filteredIngredients = ingredients.stream()
					.filter(
						ingredient ->
						ingredient.getComponent().equals(
							weighing
								.getMaterial()
									.getComponent()
						)
					)
						.findFirst();

				if (!filteredIngredients.isPresent()) {
					this.controller.confirmedMessage("Weighted component not in recipe");
					continue;
				}
				if (
					this.getIngredientSaturation(
						weightings,
						weighing.getMaterial().getComponent()
					)
					+ weighing.getAmount()
					>
					filteredIngredients.get().getUpperLimit()
				) {
					this.controller.confirmedMessage("Amount of material weighted to high");
					continue;
				}
				weightings.add(
					weighing
						.setProductBatch(this.batch)
							.setWeighedBy(user)
								.setWeight(this.weight)
				);
				Material usedMaterial = weighing.getMaterial();
				try {
					usedMaterial.take(weighing.getAmount());
				} catch (MaterialException e) {
					this.controller.confirmedMessage("Amount weighed greater than remaining material");
					continue;
				}
				Transaction transaction = session.beginTransaction();
				session.update(usedMaterial);
				session.persist(weighing);
				transaction.commit();
			}
		}
		this.complete();
		this.controller.confirmedMessage("Weighing completed");
	}

	public void run() {
		try {
			this.perform();
		} catch (RuntimeException e) {
			this.abort();
			throw new WeightingSessionException();
		} catch (Exception e) {
			throw new WeightingSessionException();
		}

	}
}
