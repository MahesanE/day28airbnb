package sg.edu.nus.iss.day28airbnb.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day28airbnb.model.Airbnb;

@Repository
public class AirbnbRepo {

    public static final String C_AIRBNB = "airbnb";

    @Autowired
    MongoTemplate template;

    public List<Airbnb> findByText(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingPhrase(text);

        MatchOperation matchOps = Aggregation.match(criteria);

        ProjectionOperation selectFields = Aggregation.project("name", "summary", "price").andExclude("_id");

        SortOperation sortOps = Aggregation.sort(Sort.Direction.DESC, "score");

        LimitOperation limitOps = Aggregation.limit(5);

        // AddFieldsOperation addFieldsOps = Aggregation.addFields().addFieldWithValue("score", new Document("$meta", "textScore"));

        Aggregation pipeline = Aggregation.newAggregation(matchOps, selectFields, sortOps, limitOps);

        List<Document> resultD = template.aggregate(pipeline, C_AIRBNB, Document.class).getMappedResults();

        List<Airbnb> result = new ArrayList<Airbnb>();
        for (Document d : resultD) {
            String name = d.getString("name");
            String summary = d.getString("summary");
            Decimal128 priceDecimal = d.get("price", Decimal128.class); 
            double price = priceDecimal.bigDecimalValue().doubleValue();
      
            Airbnb airbnb = new Airbnb(name, summary, price);
            result.add(airbnb);
          }

        return result;


    }
}
