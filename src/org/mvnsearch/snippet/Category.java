package org.mvnsearch.snippet;

import java.util.Set;

/**
 * snippet category
 *
 * @author linux_china@hotmail.com
 */
public class Category {
    private String id;  //id
    private String name;   //name
    private String icon; //icon
    private Set<Category> children; //children category

    /**
     * construct empty category
     */
    public Category() {

    }

    /**
     * construct category
     *
     * @param id   id
     * @param name name
     */
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return name;
    }
}
