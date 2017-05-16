package fsu.bignerdranch.elliottbregni.toodone;

/**
 * Created by elliottbregni on 3/12/17.
 */

import java.util.Date;
import java.util.UUID;

public class Todo {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    public Todo() {
        this(UUID.randomUUID());
    }

    public Todo(UUID id) {
        mId = id;
        mDate = new Date();
    }
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    }
