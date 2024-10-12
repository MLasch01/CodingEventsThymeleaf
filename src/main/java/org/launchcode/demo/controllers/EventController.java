package org.launchcode.demo.controllers;

import org.launchcode.demo.data.EventData;
import org.launchcode.demo.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



@Controller
@RequestMapping("events")
public class EventController {

//    private static List<String> events = new ArrayList<>();

//    private static HashMap<String, String> events = new HashMap<>();

//    Replaced above line for further exercises.  Removed when Class EventData was added
//    private static List<Event> events = new ArrayList<>();

//    static {
//        events.put("Menteaship","A fun meetup for connecting with mentors");
//        events.put("Code With Pride","A fun meetup sponsored by LaunchCode");
//        events.put("Javascripty", "An imaginary meetup for Javascript developers");
//    }

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    //lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }

    //lives at /events/create
//    @PostMapping("create")
//    public String createEvent(@RequestParam String eventName, String description) {
////        events.add(new Event(eventName));
//        events.put(eventName, description);
//        return "redirect:/events";
//    }
    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName, @RequestParam String eventDescription)
//      EventData.add(new Event(eventName, eventDescription));
      public String processCreateEventForm(@ModelAttribute Event newEvent){
        EventData.add(newEvent);
        return "redirect:/events";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm (Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:/events";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId){
        Event eventToEdit = EventData.getById(eventId);
        model.addAttribute("event", eventToEdit);
        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
        model.addAttribute("Edit Event NAME (id=ID)", title );
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description) {
        Event eventToEdit = EventData.getById(eventId);
        eventToEdit.setName(name);
        eventToEdit.setDescription(description);
        return "redirect:/events";
    }
}

//add-bootstrap branch used for Chapter 14 Examples
//package org.launchcode.demo.controllers;
//
//import org.launchcode.demo.models.Event;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Chris Bay
// */
//@Controller
//@RequestMapping("events")
//public class EventController {
//
//    private static List<Event> events = new ArrayList<>();
//
//    @GetMapping
//    public String displayAllEvents(Model model) {
//        model.addAttribute("title", "All Events");
//        model.addAttribute("events", events);
//        return "events/index";
//    }
//
//    @GetMapping("create")
//    public String displayCreateEventForm(Model model) {
//        model.addAttribute("title", "Create Event");
//        return "events/create";
//    }
//
//    @PostMapping("create")
//    public String processCreateEventForm(@RequestParam String eventName) {
//        events.add(new Event(eventName));
//        return "redirect:/events";
//    }
//
//}