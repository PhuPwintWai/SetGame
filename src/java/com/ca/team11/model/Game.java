
package com.ca.team11.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {
    @Id @Column (name="gameid") private int gameid;
//    private int gameid;
    private String groupName;

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Game{" + "gameid=" + gameid + ", groupName=" + groupName + '}';
    }
    
    
}
