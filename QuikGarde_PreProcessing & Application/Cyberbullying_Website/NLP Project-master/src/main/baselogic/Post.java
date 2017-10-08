package main.baselogic;

import main.model.Entry;
import main.model.User;
import main.dao.LoginDAO;
import main.util.DataConnect;
import main.util.SessionUtils;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;
import main.baselogic.OverAllProcess;

@ManagedBean
@SessionScoped
public class Post implements Serializable{
    private String userName;
    private String userPass;
    private String userFull;
    private String userEmail;
    public  String userEntry;
    private String entryAuthor;
    private String entryId;
    private String entryTitle;
    private String gender;
    private String bday;

    public String getGender() {return gender;}
    public void setGender(String gender){this.gender = gender;}
    public String getBday () {return bday;}
    public void setBday(String bday){this.bday = bday;}
    public String getEntryTitle() {
        return entryTitle;
    }

    public void setEntryTitle(String entryTitle) {
        this.entryTitle = entryTitle;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryAuthor() {
        return entryAuthor;
    }

    public void setEntryAuthor(String entryAuthor) {
        this.entryAuthor = entryAuthor;
    }

    public String getUserEntry() {
        return userEntry;
    }

    public void setUserEntry(String userEntry) {
        this.userEntry = userEntry;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserFull() {
        return userFull;
    }

    public void setUserFull(String userFull) {
        this.userFull = userFull;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String signUp() throws ClassNotFoundException, SQLException {
        Connection connect = null;
        PreparedStatement statement = null;

        try {

            connect = DataConnect.getConnection();


            String sql = "INSERT INTO users (username, password, fullname, email, gender, bday) VALUES (?,md5(?), ?, ?,?,?)";

            statement = connect.prepareStatement(sql);
            statement.setString(1, userName);
            statement.setString(2, userPass);
            statement.setString(3, userFull);
            statement.setString(4, userEmail);
            statement.setString(5, gender);
            statement.setString(6, bday);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println(userEntry);
            }

            statement.close();
            return "login";
        } catch (SQLException ex) {
            System.out.println("Sign up error -->" + ex.getMessage());
        } finally {
            DataConnect.close(connect);
        }

        return "login.jsf";
    }


    private User user;

    public User getUser() {
        return this.user;
    }


    public String validateUsernamePassword() {
        boolean valid = LoginDAO.validate(userName, userPass, userFull);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "timeline";

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "login";

        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }

    public String add() throws ClassNotFoundException, SQLException {
        Connection connect = null;
        PreparedStatement statement = null;

        try {

            connect = DataConnect.getConnection();
            String sql = "INSERT INTO statement (addentry,author, title) VALUES (?,?,?)";
            List<Entry> entrys = new ArrayList<>();

            statement = connect.prepareStatement(sql);
            statement.setString(1, userEntry);
            statement.setString(2, entryAuthor=userName);
            statement.setString(3, entryTitle);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new entry was inserted successfully!");
            }

            statement.close();
            return "timeline2.jsf";
        } catch (SQLException ex) {
            System.out.println("Publish error -->" + ex.getMessage());
        } finally {
            DataConnect.close(connect);
        }

        return "timeline2.jsf";
    }




    public ArrayList<Entry> getstatement() {
        return getstatement();
    }


    public List<Entry> getAuthorEntry() throws ClassNotFoundException, SQLException {
        Connection connect = null;
        PreparedStatement statement = null;

        try {

            connect = DataConnect.getConnection();

            List<Entry> userPosts = new ArrayList<>();

            statement = connect
                    .prepareStatement("select pid, author, addentry, title from statement where author =?");
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Entry userEntry = new Entry();
                userEntry.setId(rs.getString("pid"));
                userEntry.setAuthor(rs.getString("author"));
                userEntry.setBlogPost(rs.getString("addentry"));
                userEntry.setTitle(rs.getString("title"));
                userPosts.add(userEntry);

            }

            rs.close();
            statement.close();
            connect.close();

            return userPosts;
        } catch (SQLException ex) {
            System.out.println("Posts error -->" + ex.getMessage());
        } finally {
            DataConnect.close(connect);
        }
        return null;

    }
    public List<String> getPosts () throws ClassNotFoundException, SQLException {
        Connection connect = null;
        PreparedStatement statement = null;

        try {

            connect = DataConnect.getConnection();

            statement = connect
                    .prepareStatement("select addentry from statement where author =?");
            System.out.println("Username Matt: " + userName);
            statement.setString(1, "tester123");
            ResultSet rs = statement.executeQuery();

            List<String> userEntries = new ArrayList<>();

            while (rs.next()) {

                System.out.println("Fetch:");
                System.out.println(rs.getString("addentry"));
                userEntries.add(rs.getString("addentry"));
            }

            rs.close();
            statement.close();
            connect.close();

            return userEntries;
        } catch (SQLException ex) {
            System.out.println("Posts error -->" + ex.getMessage());
        } finally {
            DataConnect.close(connect);
        }
        return null;
    }


    public String newPost() {
        this.entryTitle = "";
        this.userEntry = "";
        return "timeline";
    }

    public String read(String userEntry, String entryId){

        this.entryId = entryId;
        this.userEntry = userEntry;
        this.entryTitle = entryTitle;
        return "timeline2";
    }


    public String delete(String entryId) throws ClassNotFoundException, SQLException {

        Connection connect = null;
        PreparedStatement statement = null;

        try {

            connect = DataConnect.getConnection();
            String sql = "DELETE FROM statement WHERE pid=?";

            statement = connect.prepareStatement(sql);
            statement.setString(1, entryId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
            statement.close();

            return "timeline2";
        } catch (SQLException ex) {
            System.out.println("Delete  error" + ex.getMessage());

        } finally {
            DataConnect.close(connect);
        }
        return "timeline2";
    }

    public String edit(String entryId, String entryTitle, String userEntry){

        this.entryId = entryId;
        this.entryTitle = entryTitle;
        this.userEntry = userEntry;
        System.out.println("Post id is " + entryId);
        System.out.println("Post title is " + entryTitle);
        System.out.println("Post body is " + userEntry);

        return "editEntry";
    }

    public String editPost() throws ClassNotFoundException, SQLException {
        Connection connect = null;
        PreparedStatement statement = null;

        try {
            connect = DataConnect.getConnection();
            String sql = "UPDATE statement SET title=?, addentry=? WHERE pid=?";

            statement = connect.prepareStatement(sql);
            statement.setString(1, entryTitle);
            statement.setString(2, userEntry);
            statement.setString(3, entryId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("A blog post was edited successfully!");
            }
            statement.close();
            return "timeline2";
        } catch (SQLException ex) {
            System.out.println("Edit error -->" + ex.getMessage());
        } finally {
            DataConnect.close(connect);
        }

        return "timeline2";
    }

    }