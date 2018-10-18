package ru.job4j.generic;

/**
 * The role element description extended from Base.
 *
 * @author Gregory Smirnov (artress@ngs.ru)
 * @version 1.0
 * @since 11/05/2018
 */
public class Role extends Base {
    /**
     * The role name.
     */
    private final String role;

    /**
     * The role constructor.
     *
     * @param id - the id of the role.
     * @param role - name of the role.
     */
    public Role(String id, String role) {
        super(id);
        this.role = role;
    }

    /**
     * Gets the id of the role.
     *
     * @return the id of the role.
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * Compare this role with other role.
     *
     * @param o - other role.
     * @return true if equals.
     */
    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Role role1 = (Role) o;
            result = role != null ? role.equals(role1.role) : role1.role == null;
        }
        return result;
    }

    /**
     * Calculates the hash code of this role.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return role != null ? role.hashCode() : 0;
    }

    /**
     * Presents the role in string-view.
     *
     * @return the string-view of role.
     */
    @Override
    public String toString() {
        return String.format("Role{role='%s'}", this.role);
    }
}