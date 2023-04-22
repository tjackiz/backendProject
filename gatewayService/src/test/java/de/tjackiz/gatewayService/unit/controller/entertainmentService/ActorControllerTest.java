package de.tjackiz.gatewayService.unit.controller.entertainmentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.tjackiz.gatewayService.controller.entertainmentService.ActorController;
import de.tjackiz.gatewayService.model.entertainmentService.actor.Actor;
import de.tjackiz.gatewayService.service.entertainmentService.ActorService;
import de.tjackiz.gatewayService.service.webClientService.WebClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.CREATED;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ActorController.class)
@ExtendWith(SpringExtension.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ActorService actorService;

    @MockBean
    private WebClientService webClientService;

    @InjectMocks
    private ActorController actorController;

    @Test
    public void getActors_emptySet() throws Exception {

        Mockito.when(actorService.getActorSet()).thenReturn(new HashSet<>());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/actors")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getActors_fullSet() throws Exception {

        String forename = "jane";
        String surname = "doe";
        int imdbRating = 7;
        Date birthdate = new Date();

        Set<Actor> actorSet = new HashSet<>(Arrays.asList(createActor(forename, surname, imdbRating, birthdate)));
        Mockito.when(actorService.getActorSet()).thenReturn(actorSet);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/actors")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].forename", containsInAnyOrder(forename)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].surname", containsInAnyOrder(surname)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].imdbRating", containsInAnyOrder(imdbRating)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[*].birthdate", containsInAnyOrder(birthdate))) TODO wrong format
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getActorById_actorFound() throws Exception {

        Mockito.when(actorService.getActorById(Mockito.any(UUID.class))).thenReturn(createActor());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/actors/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", is("doe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.forename", is("john")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imdbRating", is(5)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getActorById_actorNotFound() throws Exception {

        Mockito.when(actorService.getActorById(Mockito.any(UUID.class))).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/v1/actors/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createActor_created() throws Exception {

        UUID uuid = UUID.randomUUID();
        Mockito.when(actorService.createActor(Mockito.any(Actor.class))).thenReturn(uuid);

        String expectedLocation = "http://localhost/v1/actors/";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/v1/actors")
                .content(objectMapper.writeValueAsString(createActor()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", expectedLocation + uuid))
                .andDo(MockMvcResultHandlers.print());
    }

    @PostMapping
    public ResponseEntity<UUID> createActor(@RequestBody Actor actor) {
        UUID id = actorService.createActor(actor);

        // TODO rework if writing requests are async, no distinguish needed anymore
        // add location header
        if (id != null) {
            String location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(id)
                    .toUriString();
            return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, location).body(id);
        }

        return ResponseEntity.noContent().build();
    }
//
//    @PostMapping
//    public ResponseEntity<UUID> createActor(@RequestBody Actor actor) {
//        UUID id = actorService.createActor(actor);
//
//        // TODO rework if writing requests are async, no distinguish needed anymore
//        // add location header
//        if (id != null) {
//            String location = ServletUriComponentsBuilder
//                    .fromCurrentRequest()
//                    .path("/{id}")
//                    .buildAndExpand(id)
//                    .toUriString();
//            return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, location).body(id);
//        }
//
//        return ResponseEntity.noContent().build();
//    }
//
//    // TODO what return type should be used here ?
//    @PutMapping("/{id}")
//    public ResponseEntity<Actor> updateActor(@PathVariable UUID id, @RequestBody Actor actor) {
//        actor = actorService.updateActor(id, actor);
//        return actor == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(actor);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteActor(@PathVariable UUID id) {
//        actorService.deleteActor(id);
//        return ResponseEntity.noContent().build();
//    }

    /*** Utility ***/
    private Actor createActor(String forename, String surname, int imdbRating, Date birthdate) {
        Actor actor = new Actor();
        actor.setForename(forename);
        actor.setSurname(surname);
        actor.setImdbRating(imdbRating);
        actor.setBirthdate(birthdate);
        return actor;
    }

    private Actor createActor() {
        Actor actor = new Actor();
        actor.setForename("john");
        actor.setSurname("doe");
        actor.setImdbRating(5);
        actor.setBirthdate(new Date());
        return actor;
    }
}
