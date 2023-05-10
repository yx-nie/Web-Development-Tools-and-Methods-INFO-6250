package data;

import java.util.ArrayList;
import java.util.List;

public class Post {
    String _id;
    String username;
    String email;
    String posts;
    List<Reply> replies;

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Post(){

    }

    public Post(String _id, String username, String email, String posts,List<Reply> replies){
        this._id=_id;
        this.username=username;
        this.email=email;
        this.posts=posts;
        this.replies=replies;
    }

    public List<Reply> insertReply(Reply reply){
        List<Reply> replies=this.replies;
        replies.add(reply);
        return replies;
    }


    public Post(String username, String email, String posts, List<Reply> replies){
        this.username=username;
        this.email=email;
        this.posts=posts;
        this.replies=replies;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

//    @Override
//    public String toString() {
//        String repliesString = new String();
//        if(replies != null) {
//            for (Reply reply : replies) {
//                repliesString += reply.toString();
//            }
//        }
//
//        return "Post{" +
//                "_id='" + _id + '\'' +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", posts='" + posts + '\'' +
//                ", replies=" + repliesString +
//                "***this is from Post class***"+
//                '}';
//    }
}
