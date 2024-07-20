public class Event {
    String eventId;
    String organiserId;
    String eventName;
    String date;
    String time;
    String description;
    String venue;
    String organiser;
    int noOfRegistrations;

    public Event(String eventId, String organiserId, String eventName, String date, String time, String description,
            String venue, String organiser, int count) {
        this.eventId = eventId;
        this.organiserId = organiserId;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.description = description;
        this.venue = venue;
        this.organiser = organiser;
        this.noOfRegistrations = count;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getOrganiserId() {
        return organiserId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public int getNoOfRegistrations() {
        return this.noOfRegistrations;
    }
}
