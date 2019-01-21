package ru.job4j.tracker;

import java.util.List;

/**
 * Comment's description.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 23/10/2018
 */
public class Comment extends Item {
    /**
     * Comment constructor.
     *
     * @param name - the comment's name.
     * @param desc - the comment's description.
     */
    public Comment(String name, String desc) {
        super(name, desc);
    }

    /**
     * Comment constructor.
     *
     * @param author - the author of this comment.
     * @param name - the comment's name.
     * @param desc - the comment's description.
     */
    public Comment(String author, String name, String desc) {
        super(author, name, desc);
    }

    @Override
    public void setComments(List<Comment> comments) {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public List<Comment> getComments() {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public String getState() {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public void setState(String state) {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public String getCategory() {
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    @Override
    public void setCategory(String category) {
        throw new UnsupportedOperationException("This operation is not supported.");
    }
}