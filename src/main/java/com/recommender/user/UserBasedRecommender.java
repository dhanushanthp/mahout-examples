package com.recommender.user;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBasedRecommender {

	public void Recommender() throws IOException, TasteException {
		// user, product_id, rating.
		DataModel model = new FileDataModel(new File("data/user.csv"));
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		// UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
		GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}

	public static void main(String[] args) throws IOException, TasteException {
		UserBasedRecommender ubr = new UserBasedRecommender();
		ubr.Recommender();
	}
}
