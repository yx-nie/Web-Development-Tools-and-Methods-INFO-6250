package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import data.Post;
import data.PostConverter;
import data.Reply;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDBPostDAO {

    private MongoCollection<Document> MongoCollectionPosts;

    public MongoDBPostDAO(MongoClient mongo){
        this.MongoCollectionPosts=mongo.getDatabase("test").getCollection("post");
    }

    public void create(Post post){
        Document postdoc= new PostConverter().toDocument(post);
        this.MongoCollectionPosts.insertOne(postdoc);

        if(!postdoc.containsKey("_id")){
            ObjectId newId=new ObjectId();
            postdoc.put("_id", newId);
            post.set_id(newId.toString());
        }else{
            ObjectId _id=(ObjectId) postdoc.get("_id");
            post.set_id(_id.toString());
        }
    }

    public void createReply(Post post, Reply reply){
        Document document=new PostConverter().changereplyToDocument(post, reply);
        System.out.println(document+"***MongoDAO createreply document***");
        Bson filters=Filters.eq("_id", post.get_id());
        System.out.println(filters+"****MongodAO filter*********");
        Bson update= Updates.push("replies", document);
        System.out.println(update+"*******mongoDAo update***********");
        this.MongoCollectionPosts.updateOne(filters, update);
    }

    public void addReply(Post post, Reply reply){
        Document replyDoc=new PostConverter().replytoDoc(post, reply);
        System.out.println("***MongoDAO addReply document***"+replyDoc);
        System.out.println(new ObjectId(post.get_id())+ post.get_id());
        Bson filters=Filters.eq("_id", new ObjectId(post.get_id()));
        Bson update=Updates.push("replies", replyDoc);
        this.MongoCollectionPosts.updateOne(filters,update);
    }

    public void insert(Post post){
        Document postdoc=new PostConverter().toDocument(post);
    }

    public void update(Post post){
        this.MongoCollectionPosts.updateOne(Filters.eq("_id", new ObjectId(post.get_id())), new Document("$set",new PostConverter().toDocument(post)));
    }

    public void delete(String _id){
        this.MongoCollectionPosts.deleteOne(Filters.eq("_id", new ObjectId(_id)));
    }

    public void deleteReply(String post_id, String reply_id){
        Bson filters=Filters.eq("_id", new ObjectId(post_id));
        Bson update=Updates.pull("replies", Filters.eq("_id", new ObjectId(reply_id)));
        this.MongoCollectionPosts.updateOne(filters,update);
    }

    public boolean exist(String _id){
        FindIterable<Document> documents=this.MongoCollectionPosts.find(Filters.eq("_id",new ObjectId(_id))).limit(1);
        return documents!=null;
    }

    public List<Reply> getReplyList(String _id){
        Post post=getPost(_id);
        System.out.println(_id);
        System.out.println(post);

        List<Reply> replies=new ArrayList<Reply>();
        if(post.getReplies()!=null){
            replies=post.getReplies();
        }
        System.out.println("***MongoDAO getReplyList***"+replies);
        return replies;
    }

    public List<Post> getList(){
        List<Post> posts=new ArrayList<Post>();
        MongoCursor<Document> cursor=this.MongoCollectionPosts.find().iterator();

        try{
            while(cursor.hasNext()){
                Document doc=cursor.next();
                Post newpost=new PostConverter().toPost(doc);
                posts.add(newpost);
            }
        }finally {
            cursor.close();
        }
        return posts;
    }

    public  List<Post> getMyList(String username){
        List<Post> posts=new ArrayList<Post>();
        MongoCursor<Document> cursor=this.MongoCollectionPosts.find(Filters.eq("username", username)).iterator();

        try{
            while(cursor.hasNext()){
                Document doc=cursor.next();
                Post newpost=new PostConverter().toPost(doc);
                posts.add(newpost);
            }
        }finally {
            cursor.close();
        }

        return posts;
    }

    public Post getPost(String _id){
        Document doc= this.MongoCollectionPosts.find(Filters.eq("_id", new ObjectId(_id))).first();
        System.out.println("***MongodAO getPost document****"+doc);
        return new PostConverter().toPost(doc);

    }

}
