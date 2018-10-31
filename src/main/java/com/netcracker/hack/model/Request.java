package com.netcracker.hack.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "receiver")
    private UUID toId;

    @Column(name = "sender")
    private UUID fromId;

    @Column(name = "message")
    private String message;

    @ElementCollection(targetClass = RequestStatus.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "request_status", joinColumns = @JoinColumn(name = "requestStatus_id"))
    @Enumerated(EnumType.STRING)
    private Set<RequestStatus> status;

    public Request() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getToId() {
        return toId;
    }

    public void setToId(UUID toId) {
        this.toId = toId;
    }

    public UUID getFromId() {
        return fromId;
    }

    public void setFromId(UUID fromId) {
        this.fromId = fromId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<RequestStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<RequestStatus> status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(toId, request.toId) &&
                Objects.equals(fromId, request.fromId) &&
                Objects.equals(message, request.message) &&
                status == request.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, toId, fromId, message, status);
    }
}
