package dev.thomas.models;

import java.time.LocalDate;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends AbstractReimbursement {
    private String submitted;
    private String resolved;
    private String description;
    private int author_id;
    private int resolver_id;
    private ReimbursementEnum reimbursementEnum;
    private ReimbursementStatus reimbursementStatus;
    /*private String date;
    private int request_id;
    private int amount;
    private String time;
    private String location;
    private String justification;*/

    public Reimbursement() {
        super();
        //this.date = date;
    }

    /**
     * This includes the minimum parameters needed for the {@link dev.thomas.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */

    public Reimbursement(int id, Status status, User author, User resolver, int amount, String date, String time, String location, String description, String justification) {
        super(id, status, author, resolver, amount, date, time, location, description, justification);
    }

    public String getReimbursementStatus() {
        if (reimbursementStatus == ReimbursementStatus.APPROVED) {
            return "APPROVED";
        }
        if (reimbursementStatus == ReimbursementStatus.DENIED) {
            return "DENIED";
        } else {
            return "PENDING";
        }
    }

    public ReimbursementStatus setReimbursementStatus(String reimbursementStatus) {
        if (reimbursementStatus.equalsIgnoreCase("APPROVED")) {
            return this.reimbursementStatus = ReimbursementStatus.APPROVED;
        }
        if (reimbursementStatus.equalsIgnoreCase("PENDING")) {
            return this.reimbursementStatus = ReimbursementStatus.PENDING;
        }
        if (reimbursementStatus.equalsIgnoreCase("DENIED")) {
            return this.reimbursementStatus = ReimbursementStatus.DENIED;
        }
        return null;
    }


    public String getResolved() {
        return resolved;
    }

    public int getResolver_id() {
        return resolver_id;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setResolver_id(int resolver_id) {
        this.resolver_id = resolver_id;
    }

    /*public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }


    public String getJustification() {
        return justification;
    }*/

    public ReimbursementEnum setReimbursementType(String reimbursementType) {
        if (reimbursementType.equalsIgnoreCase("FOOD")) {
            return this.reimbursementEnum = ReimbursementEnum.FOOD;
        }
        if (reimbursementType.equalsIgnoreCase("TRAVEL")) {
            return this.reimbursementEnum = ReimbursementEnum.TRAVEL;
        }
        if (reimbursementType.equalsIgnoreCase("LODGING")) {
            return this.reimbursementEnum = ReimbursementEnum.LODGING;
        }
        if (reimbursementType.equalsIgnoreCase("OTHER")) {
            return this.reimbursementEnum = ReimbursementEnum.OTHER;
        }
        return null;
    }

    public String getSubmitted() {
        if (submitted == null) {
            submitted = LocalDate.now().toString();
        }
        return submitted;
    }

    public String getDescription() {
        return description;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getReimbursementEnum() {
        if (reimbursementEnum == ReimbursementEnum.FOOD) {
            return "FOOD";
        }
        if (reimbursementEnum == ReimbursementEnum.TRAVEL) {
            return "TRAVEL";
        }
        if (reimbursementEnum == ReimbursementEnum.LODGING) {
            return "LODGING";
        }
        return "OTHER";
    }


}
