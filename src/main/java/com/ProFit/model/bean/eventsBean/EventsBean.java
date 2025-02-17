package com.ProFit.model.bean.eventsBean;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ProFit.model.bean.majorsBean.MajorBean;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class EventsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "event_id")
	private String eventId;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "is_event_active")
	private int isEventActive;

	@Column(name = "event_category")
	private int eventCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_major", insertable = false, updatable = false)
	private MajorBean eventMajor;

	@Column(name = "event_major")
	private Integer eventMajorId;

	@Column(name = "event_publish_date")
	private LocalDateTime eventPublishDate;

	@Column(name = "event_start_date")
	private LocalDateTime eventStartDate;

	@Column(name = "event_end_date")
	private LocalDateTime eventEndDate;

	@Column(name = "event_part_start_date")
	private LocalDateTime eventPartStartDate;

	@Column(name = "event_part_end_date")
	private LocalDateTime eventPartEndDate;

	@Column(name = "event_amount")
	private int eventAmount;

	@Column(name = "event_location")
	private String eventLocation;

	@Column(name = "event_participant_maximum")
	private int eventParticipantMaximum;

	@Column(name = "event_description")
	private String eventDescription;

	@Column(name = "event_note")
	private String eventNote;

	public EventsBean() {
	}

	public EventsBean(String eventId, String eventName, int isEventActive, int eventCategory, MajorBean eventMajor,
			LocalDateTime eventPublishDate, LocalDateTime eventStartDate, LocalDateTime eventEndDate,
			LocalDateTime eventPartStartDate, LocalDateTime eventPartEndDate, int eventAmount, String eventLocation,
			int eventParticipantMaximum, String eventDescription, String eventNote) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.isEventActive = isEventActive;
		this.eventCategory = eventCategory;
		this.eventMajor = eventMajor;
		this.eventPublishDate = eventPublishDate;
		this.eventStartDate = eventStartDate;
		this.eventEndDate = eventEndDate;
		this.eventPartStartDate = eventPartStartDate;
		this.eventPartEndDate = eventPartEndDate;
		this.eventAmount = eventAmount;
		this.eventLocation = eventLocation;
		this.eventParticipantMaximum = eventParticipantMaximum;
		this.eventDescription = eventDescription;
		this.eventNote = eventNote;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public int getIsEventActive() {
		return isEventActive;
	}

	public void setIsEventActive(int isEventActive) {
		this.isEventActive = isEventActive;
	}

	public int getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(int eventCategory) {
		this.eventCategory = eventCategory;
	}

	public MajorBean getEventMajor() {
		return eventMajor;
	}

	public void setEventMajor(MajorBean eventMajor) {
		this.eventMajor = eventMajor;
	}

	public int getEventMajorId() {
		return eventMajorId;
	}

	public void setEventMajorId(int eventMajorId) {
		this.eventMajorId = eventMajorId;
	}

	public LocalDateTime getEventPublishDate() {
		return eventPublishDate;
	}

	public void setEventPublishDate(LocalDateTime eventPublishDate) {
		this.eventPublishDate = eventPublishDate;
	}

	public LocalDateTime getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(LocalDateTime eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public LocalDateTime getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(LocalDateTime eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public LocalDateTime getEventPartStartDate() {
		return eventPartStartDate;
	}

	public void setEventPartStartDate(LocalDateTime eventPartStartDate) {
		this.eventPartStartDate = eventPartStartDate;
	}

	public LocalDateTime getEventPartEndDate() {
		return eventPartEndDate;
	}

	public void setEventPartEndDate(LocalDateTime eventPartEndDate) {
		this.eventPartEndDate = eventPartEndDate;
	}

	public int getEventAmount() {
		return eventAmount;
	}

	public void setEventAmount(int eventAmount) {
		this.eventAmount = eventAmount;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public int getEventParticipantMaximum() {
		return eventParticipantMaximum;
	}

	public void setEventParticipantMaximum(int eventParticipantMaximum) {
		this.eventParticipantMaximum = eventParticipantMaximum;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventNote() {
		return eventNote;
	}

	public void setEventNote(String eventNote) {
		this.eventNote = eventNote;
	}

	@Override
	public String toString() {
		return "EventsBean [eventId=" + eventId + ", eventName=" + eventName + ", isEventActive=" + isEventActive
				+ ", eventCategory=" + eventCategory + ", eventMajor=" + eventMajor + ", eventPublishDate="
				+ eventPublishDate + ", eventStartDate=" + eventStartDate + ", eventEndDate=" + eventEndDate
				+ ", eventPartStartDate=" + eventPartStartDate + ", eventPartEndDate=" + eventPartEndDate
				+ ", eventAmount=" + eventAmount + ", eventLocation=" + eventLocation + ", eventParticipantMaximum="
				+ eventParticipantMaximum + ", eventDescription=" + eventDescription + ", eventNote=" + eventNote + "]";
	}

}
