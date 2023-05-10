package data;


import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class PostConverter {
    public PostConverter(){

    }

    public Document changereplyToDocument(Post post, Reply reply){
        Document doc;
        if(reply.get_id()!=null){
            doc=new Document("_id", reply.get_id())
                    .append("post_rid", post.get_id())
                    .append("username", reply.getUsername())
                    .append("email", reply.getEmail())
                    .append("post", reply.getPost());
        }else {
            doc= new Document()
                    .append("post_rid", post.get_id())
                    .append("username", reply.getUsername())
                    .append("email", reply.getEmail())
                    .append("post", reply.getPost());

            System.out.println("***Post Converteer changereplyToDocument***"+doc);
        }
        return doc;
    }

    public Document replytoDoc(Post post, Reply reply){
        Document doc;
        if(reply.get_id()!=null){
            doc=new Document("_id", reply.get_id())
                    .append("post_rid", post.get_id())
                    .append("username", reply.getUsername())
                    .append("email", reply.getEmail())
                    .append("post", reply.getPost());
        }else {
            doc= new Document("_id", new ObjectId())
                    .append("post_rid", post.get_id())
                    .append("username", reply.getUsername())
                    .append("email", reply.getEmail())
                    .append("post", reply.getPost());
        }
        return doc;
    }
    public Document replyToDocument(Post post, Reply reply){
        Document doc;
        if(reply.get_id()!=null){
            doc=new Document("_id", reply.get_id())
                    .append("post_rid", post.get_id())
                    .append("username", reply.getUsername())
                    .append("email", reply.getEmail())
                    .append("post", reply.getPost());
        }else {
            doc= new Document()
                    .append("post_rid", post.get_id())
                    .append("username", reply.getUsername())
                    .append("email", reply.getEmail())
                    .append("post", reply.getPost());


        }
        Document postdoc=new Document()
                .append("_id", post.get_id())
                .append("username", post.getUsername())
                .append("email", post.getEmail())
                .append("post", post.getPosts());
        if(post.getReplies()!=null){
            postdoc.append("replies", post.getReplies());
        }else{
            postdoc.append("replies", new ArrayList<Document>());
        }
        postdoc.append("replies", doc);
        System.out.println("****PostConverter replyToDocument*****"+postdoc);
        return postdoc;
    }

    public Document toDocument(Post post){
        Document doc;
        if(post.getReplies()!=null){
            List<Document> replyDocs= new ArrayList<Document>();
            for(Reply reply: post.replies){
                Document replydoc =replyToDocument(post, reply);
                replyDocs.add(replydoc);
            }
//            if(post.get_id()!=null) {
            doc = new org.bson.Document("_id", post.get_id())
                    .append("username", post.getUsername())
                    .append("email", post.getEmail())
                    .append("post", post.getPosts())
                    .append("replies", replyDocs);
//            }
//            else{
//                doc = new org.bson.Document()
//                        .append("username", post.getUsername())
//                        .append("email", post.getEmail())
//                        .append("post", post.getPosts())
//                        .append("replies", replyDocs);
//            }
        }else{
            if(post.get_id()!=null) {
                doc = new org.bson.Document("_id", post.get_id())
                        .append("username", post.getUsername())
                        .append("email", post.getEmail())
                        .append("post", post.getPosts())
                        .append("replies", new ArrayList<Document>());
            }else{
                doc = new org.bson.Document()
                        .append("username", post.getUsername())
                        .append("email", post.getEmail())
                        .append("post", post.getPosts())
                        .append("replies", new ArrayList<Document>());
            }
        }

        return doc;
    }

    public Reply toReply(Document replydoc){
        Reply reply=new Reply();
        ObjectId objectId=replydoc.getObjectId(("_id"));
        if(objectId!=null){
            reply.set_id(objectId.toString());
        }

        reply.setPost((String) replydoc.get("post"));
        reply.setPost_rid((String) replydoc.get("post_rid"));
        reply.setUsername((String) replydoc.get("username"));
        reply.setEmail((String) replydoc.get("email"));

        return reply;
    }


    public Post toPost(Document postdoc){
        Post post=new Post();
        ObjectId objectId=postdoc.getObjectId("_id");
        if(objectId!=null){
            post.set_id(objectId.toString());
        }

        post.setUsername((String)postdoc.get("username"));
        post.setPosts((String)postdoc.get("post"));
        post.setEmail((String)postdoc.get("email"));


        List<Document> replydocs= (List<Document>) postdoc.get("replies");
        List<Reply> replieses= new ArrayList<Reply>();
        if(replydocs!=null && !replydocs.isEmpty()){
            for(int i=0; i<replydocs.size(); i++){
                Reply reply=this.toReply(replydocs.get(i));
                replieses.add(reply);
            }
            post.setReplies(replieses);
        }

        System.out.println("***PostConverter toPost***"+post);
        System.out.println("***PostConverter toPost***"+post.get_id());
        return post;
    }

}
