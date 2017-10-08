package main.model;


import javax.faces.bean.SessionScoped;

@SessionScoped
public class Entry{

    public String blogPost;

    public String author;

    public String id;

    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    boolean editable;
    public Entry() {

    }

    public String getBlogPost() {
        return blogPost;
    }



    public Entry(String blogPost)
    {
        this.blogPost = blogPost;
    }


    public void setBlogPost(String blogPost) {
        this.blogPost = blogPost;
    }
}
