package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Item describes requests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.1
 * @since 07/02/2018
 */
public class Item {
    /**
     * The author of this request.
     */
    private String author;

    /**
     * The request id.
     */
    private String id;

    /**
     * The request name.
     */
    private String name;

    /**
     * The request description.
     */
    private String desc;

    /**
     * The request creation time.
     */
    private long creation;

    /**
     * The comments of the request.
     */
    private List<Comment> comments;

    /**
     * The current state of this request
     */
    private String state;

    /**
     * The category of this request.
     */
    private String category;

    public Item(String id, String author, String name, String desc, long creation, String state, String category) {
        this.author = author;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.creation = creation;
        this.comments = new ArrayList<Comment>();
        this.state = state;
        this.category = category;
    }

    /**
     * Item constructor.
     *
     * @param author - the author of this request.
     * @param name - the request's name.
     * @param desc - the request's description.
     * @param category - the category of this request.
     */
    public Item(String author, String name, String desc, String category) {
        this.author = author;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.creation = System.currentTimeMillis();
        this.state = Constants.NEW_STATE;
        this.comments = new ArrayList<Comment>();
    }

    /**
     * Item constructor.
     *
     * @param author - the author of this request.
     * @param name - the request's name.
     * @param desc - the request's description.
     */
    public Item(String author, String name, String desc) {
        this(author, name, desc, Constants.DEFAULT_CATEGORY);
    }

    /**
     * Item constructor.
     *
     * @param name - the request's name.
     * @param desc - the request's description.
     */
    public Item(String name, String desc) {
        this(Constants.DEFAULT_AUTHOR, name, desc, Constants.DEFAULT_CATEGORY);
    }

    /**
     * Id setter.
     *
     * @param id - the request id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Id getter.
     *
     * @return id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Name setter.
     *
     * @param name - the request name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Name getter.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Description setter.
     *
     * @param desc - the request description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Description getter.
     *
     * @return description.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Creation setter.
     *
     * @param creation - the request creation date.
     */
    public void setCreation(long creation) {
        this.creation = creation;
    }

    /**
     * Creation date getter.
     *
     * @return creation date.
     */
    public long getCreation() {
        return this.creation;
    }

    /**
     * Comments setter.
     *
     * @param comments - the request comments.
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    /**
     * Comments getter.
     *
     * @return comments.
     */
    public List<Comment> getComments() {
        return this.comments;
    }

    /**
     * Gets the author of this request.
     *
     * @return - the author of this request.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Sets the specified author for this request.
     *
     * @param author - the specified author for this request.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the current state of this request.
     *
     * @return the current state of this request.
     */
    public String getState() {
        return this.state;
    }

    /**
     * Sets the current state of this request.
     *
     * @param state - the current state of this request.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the category of this request.
     *
     * @return - the category of this request.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the category of this request.
     *
     * @param category - the category of this request.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Checks this item for equivalence for other item.
     *
     * @param o - the other item.
     * @return - true if items are equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Item item = (Item) o;
            result = Objects.equals(this.name, item.name) && Objects.equals(this.desc, item.desc);
        }
        return result;
    }

    /**
     * Counts the hashcode.
     *
     * @return the hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.desc);
    }

    @Override
    public String toString() {
        return "Item{"
                + "author='" + author + '\''
                + ", id='" + id + '\''
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", creation=" + creation
                + ", comments=" + comments
                + ", state='" + state + '\''
                + ", category='" + category + '\'' + '}';
    }
}