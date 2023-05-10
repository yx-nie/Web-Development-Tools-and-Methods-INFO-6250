package data;

public class Reply {
    String _id;
    String post_rid;
    String username;
    String email;
    String post;

    public Reply(){

    }
    public Reply(String post_rid, String username, String email, String post){
        this.post_rid=post_rid;
        this.username=username;
        this.email=email;
        this.post=post;
    }



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPost_rid() {
        return post_rid;
    }

    public void setPost_rid(String post_rid) {
        this.post_rid = post_rid;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

//    @Override
//    public String toString() {
//        return "Reply{" +
//                "_id='" + _id + '\'' +
//                ", post_rid='" + post_rid + '\'' +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", post='" + post + '\'' +
//                "***this is from Reply class***"+
//                '}';
//    }
}
