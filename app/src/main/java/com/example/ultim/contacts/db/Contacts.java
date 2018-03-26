package com.example.ultim.contacts.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.support.annotation.NonNull;

@Entity
public class Contacts {
    @PrimaryKey
    @NonNull
    private int contact_ID;
    private String first_name;
    private String last_name;
    private String phone_number;

    public Contacts(@NonNull int contact_ID, String first_name, String last_name, String phone_number) {
        this.contact_ID = contact_ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
    }

    protected Contacts(Parcel in){
        contact_ID = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        phone_number = in.readString();

    }

    /**
     * GETTERS AND SETTERS FOR THE APPLICATION
     * @return
     */

    @NonNull
    public int getContact_ID() {
        return contact_ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
