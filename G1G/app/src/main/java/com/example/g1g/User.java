package com.example.g1g;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class User
{
    //properties
    //for views
    PropertyChangeSupport pcs;

    //ID properties
    private String name;
    UserDatabase database;
    //private String surName;
    private int    registeredSemester;
    private String department;
    private String bilkentMail;

    //App related properties
    private ArrayList<Book> pastPurchases;
    private ArrayList<Book> wishList;
    private ArrayList<Book> sellingBooks;
    private String          userName;
    private String          passWord;
    private int             rating;

    //constructors
    public User( String name, String password, String userName, int registeredSemester, String department, String bilkentMail)
    {
        pcs = new PropertyChangeSupport( this);
        this.name = name;
        this.userName = userName;
        this.registeredSemester = registeredSemester;
        this.department = department;
        this.bilkentMail = bilkentMail;
        this.passWord = password;
        //setting up in database
        //database = new UserDatabase( name, userName, registeredSemester, department, bilkentMail);
        pastPurchases = new ArrayList<Book>();
        wishList = new ArrayList<Book>();
        sellingBooks = new ArrayList<Book>();
    }

    //methods
    //get and set methods
    public void addSellingBook( Book book)
    {
        sellingBooks.add( book);
    }

    public void addWishListBook( Book book)
    {
        wishList.add( book);
    }

    public void addPastPurchases( Book book)
    {
        pastPurchases.add( book);
    }

    //set and get methods for user personal properties( Properties that exist outside of this app)
    public void setName( String name)
    {
        this.name = name;
        pcs.firePropertyChange( "name", null, null);
    }

    public String getName()
    {
        return name;
    }

    public void setRegisteredSemester( int registeredSemester)
    {
        this.registeredSemester = registeredSemester;
        pcs.firePropertyChange( "registeredSemester", null, null);
    }

    public int getRegisteredSemester()
    {
        return registeredSemester;
    }

    public void setDepartment( String department)
    {
        this.department = department;
        pcs.firePropertyChange( "department", null, null);
    }

    public String getDepartment()
    {
        return department;
    }

    public void setBilkentMail( String bilkentMail)
    {
        this.bilkentMail = bilkentMail;
        pcs.firePropertyChange( "bilkentMail", null, null);
    }

    public String getBilkentMail()
    {
        return bilkentMail;
    }

    //set and get methods for app related properties
    public void setUserName( String userName)
    {
        this.userName = userName;
        pcs.firePropertyChange( "userName", null, null);
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setRating( int rating)
    {
        this.rating = rating;
        pcs.firePropertyChange( "rating", null, null);
    }

    public void setPassWord( String password)
    {
        this.passWord = password;
    }

    public int getRating()
    {
        return rating;
    }

    public String toString()
    {
        return userName + " " + bilkentMail;
    }
}