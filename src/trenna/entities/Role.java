/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.entities;

/**
 *
 * @author moham
 */
public class Role {
    private int idRole;
    private String description;
    private String name;

    public Role() {
    }

    public Role(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public Role(int idRole, String description, String name) {
        this.idRole = idRole;
        this.description = description;
        this.name = name;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" + "idRole=" + idRole + ", description=" + description + ", name=" + name + '}';
    }
    
    
}
