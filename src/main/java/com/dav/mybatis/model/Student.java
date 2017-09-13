package com.dav.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.dav.mybatis.common.custom.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// TODO: Auto-generated Javadoc
/**
 * The Class Student.
 */
public class Student implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The student id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /** The student name. */
    @NotNull
    @Size(min = 2, max = 255)
    @Pattern(regexp = "^[a-zA-Z\\d\\p{L}]+( [a-zA-Z\\d\\p{L}]+)*$", message = "{Pattern.student.name}")
    private String name;

    /** The student code. */
    @NotNull
    @Size(min = 2, max = 11, message = "{Size.student.code}")
    @Pattern(regexp = "\\d+", message = "{Pattern.student.code}")
    private String code;

    /** The address. */
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\d,.\\p{L}]+( [a-zA-Z\\d,.\\p{L}]+)*$", message = "{Pattern.student.address}")
    private String address;

    /** The average score. */
    @NotNull(message = "{NotNull.student.averageScore}")
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double averageScore;

    /** The date of birth. */
    @Past
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date dateOfBirth;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the average score.
     *
     * @return the average score
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * Sets the average score.
     *
     * @param averageScore the new average score
     */
    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Gets the date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     *
     * @param dateOfBirth the new date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
