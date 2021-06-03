package com.example.g1g;

public class Book
{
    //Variables
    private String title;
    private String author;
    private int year;
    private String subject;
    private double cost;

    //Constructor
    public Book(String title, int year, String author, String subject, double cost)
    {   this.title=title;
        this.year=year;
        this.author=author;
        this.subject = subject;
        this.cost=cost;
    }


    //Get Methods
    public String getTitle()
    {
        return title;
    }
    public int getYear()
    {
        return year;
    }
    public String getAuthor()
    {
        return author;
    }

    public String getPublisher()
    {
        return subject;
    }
    public double getCost()
    {
        return cost;
    }


    //Set Methods

    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setYear(int year)
    {
        this.year=year;
    }
    public void setAuthor(String author)
    {
        this.author=author;
    }
    public void setPublisher(String publisher)
    {
        this.subject =publisher;
    }
    public void setCost(double cost)
    {
        this.cost=cost;
    }


    public String toString()
    {
        String info;

        info = "Book Title : " + getTitle();
        info = info + "Publish date : " + getYear();
        info = info + "Book author : " + getAuthor();
        info = info + "Book Publisher : " + getPublisher();
        info = info + "Cost : " + getCost();
        return info;
    }
}
