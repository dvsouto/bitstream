//package br.com.bitnary.bitstream.domain.userProfile;
//
//import br.com.bitnary.bitstream.domain.user.User;
//import br.com.bitnary.bitstream.domain.user.UserRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserProfileRepositoryTest {
//    @Autowired
//    private static UserRepository userRepository;
//
//    @Autowired
//    private static UserProfileRepository userProfileRepository;
//
//    private static User user;
//
//    @BeforeAll()
//    static void createUser() {
//        user = userRepository.save(User.builder()
//                .name("john Doe")
//                .email("john@doe.com")
//                .password("password")
//                .build()
//        );
//    }
//
//    @Test
//    @Order(1)
//    void testCreateUserProfile() {
//        userProfileRepository.save(user.getId(), UserProfile.builder()
//                .name("John")
//                .build()
//        );
//
//        userProfileRepository.save(user.getId(), UserProfile.builder()
//                .name("Doe")
//                .build()
//        );
//
//        userProfileRepository.save(user.getId(), UserProfile.builder()
//                .name("Zezinho")
//                .build()
//        );
//
//        List<UserProfile> profiles = userProfileRepository.findAll();
//
//        assertThat(profiles.size()).isEqualTo(3);
//        assertTrue(profiles.stream().anyMatch(profile -> profile.getName().equals("John")));
//        assertTrue(profiles.stream().anyMatch(profile -> profile.getName().equals("Doe")));
//        assertTrue(profiles.stream().anyMatch(profile -> profile.getName().equals("Zezinho")));
//    }
//
//    @Test
//    @Order(2)
//    void testGetUserProfilesByUser() {
//        List<UserProfile> profiles = userProfileRepository.findByUserId(user.getId());
//
//        assertThat(profiles.size()).isEqualTo(3);
//        assertTrue(profiles.stream().anyMatch(profile -> profile.getName().equals("John")));
//        assertTrue(profiles.stream().anyMatch(profile -> profile.getName().equals("Doe")));
//        assertTrue(profiles.stream().anyMatch(profile -> profile.getName().equals("Zezinho")));
//    }
//
//}
