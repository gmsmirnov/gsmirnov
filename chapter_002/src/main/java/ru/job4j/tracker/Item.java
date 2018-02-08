package ru.job4j.tracker;

/**
 * Class Item describes requests.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 07/02/2018
 */
public class Item {
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
    private String[] comments;

    /**
     * Item constructor.
     *
     * @param name - the request name.
     * @param desc - the request description.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.creation = System.currentTimeMillis();
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
    public void setCreqtion(long creation) {
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
    public void setComments(String[] comments) {
        this.comments = comments;
    }

    /**
     * Comments getter.
     *
     * @return comments.
     */
    public String[] getComments() {
        return this.comments;
    }
}
