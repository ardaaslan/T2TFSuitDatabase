/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.domain;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Arda
 */
@JsonAutoDetect
public class T2TFProject {
    private String userName;
    private String projectName;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    

}
