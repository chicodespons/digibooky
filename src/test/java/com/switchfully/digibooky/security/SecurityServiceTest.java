package com.switchfully.digibooky.security;

import com.switchfully.digibooky.controller.BookController;
import com.switchfully.digibooky.controller.UserController;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.models.*;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private BookController bookController;
    @Autowired
    private UserController userController;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Nested
    @DisplayName("Basic tests")
    class basicTests {
        @Test
        @DisplayName("Testing validation to not throw when correct email and password")
        void givenCorrectEmailAndPaswor_whenValidateAuthorization_thenNoErrorCanBeThrown() {
            //given
            String correctInput = "sven@mail.be:azerty";
            byte[] inputEncoded = Base64.getEncoder().encode(correctInput.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);
            //
            Feature feature = Feature.GET_ALL_BOOKS;

            //then
            Assertions.assertDoesNotThrow(() -> securityService.validateAuthorization(authorizationString, feature));
        }

        @Test
        @DisplayName("Testing validation to throw error when email is not in userlist")
        void givenEmailThatIsNotKnow_whenValidateAuthorization_thenErrorShouldBeThrown() {
            //given
            String incorrectEmail = "unkown@mail.be:azerty";
            byte[] inputEncoded = Base64.getEncoder().encode(incorrectEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);
            //
            Feature feature = Feature.GET_ALL_BOOKS;
            //when

            //then
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> securityService.validateAuthorization(authorizationString, feature));
        }

        @Test
        @DisplayName("Testing validation to throw error when email correct but password is incorrect")
        void givenEmailThatIsKnowButWrongPasword_whenValidateAuthorization_thenErrorMessageShouldBeThrown() {
            //given
            String wrongPasword = "sven@mail.be:wrongPasword";
            byte[] inputEncoded = Base64.getEncoder().encode(wrongPasword.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            Feature feature = Feature.GET_ALL_BOOKS;
            //when

            //then
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> securityService.validateAuthorization(authorizationString, feature));
        }

        @Test
        @DisplayName("Testing validation to throw correct error message when User has no access to Feature")
        void givenAMember_whenaccesssingFeatureThatIsNotAllowed_thenErrorMessageShouldBeThrown() {
            //given
            String correctMember = "testMember@mail.com:testMember";
            byte[] inputEncoded = Base64.getEncoder().encode(correctMember.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);
            //
            Feature feature = Feature.TEST_FEATURE;
            //when
            Throwable ex = catchThrowable(() -> securityService.validateAuthorization(authorizationString, feature));
            //then
            Assertions.assertEquals("User does not have access.", ex.getMessage());
        }
    }

    @Nested
    @DisplayName("Testing access story 1 get all books")
    class testaccessGetAllBooks {
        @Test
        @DisplayName("When asking the book controller to get all books as a user, we should not get an exception and get all books")
        void whenAskingToGetAllBooksAsAUser_shouldNotThrowException() {
            //given
            Member member = new Member("12315615", "BookController1", "Sven", "getAllBooksSecurityTest@mail.be", "1591", "teststraat", 156, 156, "Brussel");
            userRepository.addMember(member);

            String correctEmail = "getAllBooksSecurityTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertDoesNotThrow(() -> bookController.getAllBooks(authorizationString));
        }

        @Test
        @DisplayName("When asking the the book controller to get all books as a not known user, we should get an exception thrown")
        void whenAskingToGetAllBooksAsANotKnowAUser_shouldThrowException() {
            //given
            String correctEmail = "unknownPersonForTestingSecurity@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);
            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.getAllBooks(authorizationString));
        }

    }

    @Nested
    @DisplayName("Testing access story 3 search book by ISBN")
    class testaccessGetBookByISBN {
        @Test
        @DisplayName("When asking the controller to get a book by ISBN as a not known user, we should get an exception")
        void whenAskingToGetBookByISBNAsANotKnownUser_shouldThrowException() {
            //given
            Book book = new Book("tesISBNforBookController1", "title of test book", new Author("sven", "boeckstaens"));
            bookRepository.addBook(book);
            String correctEmail = "unknownPersonForTestingSecurity@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.getBookByISBN(authorizationString, book.getISBN()));
        }
    }

    @Nested
    @DisplayName("Testing access story 4 search book by title")
    class testaccessGetBookByTitle {
        @Test
        @DisplayName("When asking the controller to get a book by title as a not known user, we should get an exception")
        void whenAskingToGetBookByISBNAsANotKnownUser_shouldThrowException() {
            //given
            Book book = new Book("tesISBNforBookController1", "title of test book", new Author("sven", "boeckstaens"));
            bookRepository.addBook(book);
            String correctEmail = "unknownPersonForTestingSecurity@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.getBookByTitle(authorizationString, book.getISBN()));
        }
    }
    @Nested
    @DisplayName("Testing access story 5 search book by author")
    class testaccessGetBookByAuthor {
        @Test
        @DisplayName("When asking the controller to get a book by title as a not known user, we should get an exception")
        void whenAskingToGetBookByAuthorAsANotKnownUser_shouldThrowException() {

            String correctEmail = "unknownPersonForTestingSecurity@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.getBookByAuthor(authorizationString, "author"));
        }
    }

    @Nested
    @DisplayName("Testing access story 7 view members as Admin only")
    class testaccessToRegisterAMember {
        @Test
        @DisplayName("When asked to see all members as an member, an exception should be thrown")
        void whenAskingToSeeAllMembersAsAMember_shouldThrowException() {
            //given
            Member member = new Member("12315615", "BookController1", "Sven", "vieuwMembersAsAMemberTest@mail.be", "1591", "teststraat", 156, 156, "Brussel");
            userRepository.addMember(member);
            String correctEmail = "vieuwMembersAsAMemberTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> userController.getAllMembers(authorizationString));
        }

        @Test
        @DisplayName("When asked to see all members as an librarian, an exception should be thrown")
        void whenAskingToSeeAllMembersAsALibrarian_shouldThrowException() {
            //given
            User librarian = new User("12315615", "BookController1", "Sven", "vieuwMembersAsALibrarianTest@mail.be", Role.LIBRARIAN);
            userRepository.addUser(librarian);
            String correctEmail = "vieuwMembersAsALibrarianTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> userController.getAllMembers(authorizationString));
        }

        @Test
        @DisplayName("When asked to see all members as an Admin, an exception should not be thrown")
        void whenAskingToSeeAllMembersAsAAdmin_shouldNotThrowException() {
            //given
            User admin = new User("12315615", "BookController1", "Sven", "vieuwMembersAsAnAdminTest@mail.be", Role.ADMIN);
            userRepository.addUser(admin);
            String correctEmail = "vieuwMembersAsAnAdminTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertDoesNotThrow(() -> userController.getAllMembers(authorizationString));
        }
    }

    @Nested
    @DisplayName("Testing access story 10 register a new book as librarian only")
    class testaccessToRegisterANewBook {
        @Test
        @DisplayName("When asked to register a new book as a member, an exception should be thrown")
        void whenAskedToRegisterANewBookAsMember_shouldThrowException() {
            //given
            Member member = new Member("12315615", "BookController1", "Sven", "addABookAsAMemberTest@mail.be", "1591", "teststraat", 156, 156, "Brussel");
            userRepository.addMember(member);
            BookDto bookToRegister = new BookDto("159195195159", "Title", new Author("d", "e"));
            String correctEmail = "addABookAsAMemberTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.registerNewBook(authorizationString, bookToRegister));
        }

        @Test
        @DisplayName("When asked to register a new book as a librarian, an exception should not be thrown")
        void whenAskedToRegisterABookAsALibrarian_shouldThrowException() {
            //given
            User librarian = new User("12315615", "BookController1", "Sven", "adBookAsALibrarianTest@mail.be", Role.LIBRARIAN);
            userRepository.addUser(librarian);
            BookDto bookToRegister = new BookDto("159195195159", "Title", new Author("d", "e"));

            String correctEmail = "adBookAsALibrarianTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertDoesNotThrow(() -> bookController.registerNewBook(authorizationString, bookToRegister));
        }

        @Test
        @DisplayName("When asked to register a new book as an Admin, an exception should be thrown")
        void whenAskedToRegisterANewBookAsAdmin_shouldThrowException() {
            //given
            User admin = new User("12315615", "BookController1", "Sven", "adBookAsAdminTest@mail.be", Role.ADMIN);
            userRepository.addUser(admin);
            BookDto bookToRegister = new BookDto("159195195159", "Title", new Author("d", "e"));
            String correctEmail = "adBookAsAdminTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.registerNewBook(authorizationString, bookToRegister));
        }
    }
    @Nested
    @DisplayName("Testing access story 10B update a book as librarian only")
    class testaccessToUpdateAbook {
        @Test
        @DisplayName("When asked to update a new book as a member, an exception should be thrown")
        void whenAskedToUpdateANewBookAsMember_shouldThrowException() {
            //given
            Member member = new Member("12315615", "BookController1", "Sven", "updateABookAsAMemberTest@mail.be", "1591", "teststraat", 156, 156, "Brussel");
            userRepository.addMember(member);

            Book bookInDatabaseToChange = new Book("789789789789", "efef", new Author("efz", "zefzf"));
            bookRepository.addBook(bookInDatabaseToChange);
            BookToUpdateToDto bookToRegister = new BookToUpdateToDto("tile changed", new Author("d", "e"), "summary to put", false);

            String correctEmail = "updateABookAsAMemberTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.updateBook(bookToRegister, bookInDatabaseToChange.getISBN(), authorizationString));
        }

        @Test
        @DisplayName("When asked to update a book as a librarian, an exception should not be thrown")
        void whenAskedToUpdateABookAsALibrarian_shouldThrowException() {
            //given
            User librarian = new User("12315615", "BookController1", "Sven", "updateBookAsALibrarianTest@mail.be", Role.LIBRARIAN);
            userRepository.addUser(librarian);

            Book bookInDatabaseToChange = new Book("789sdf789sf789sdf789", "efef", new Author("efz", "zefzf"));
            bookRepository.addBook(bookInDatabaseToChange);
            BookToUpdateToDto bookToRegister = new BookToUpdateToDto("tile changed", new Author("d", "e"), "summary to put", false);

            String correctEmail = "updateBookAsALibrarianTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertDoesNotThrow(() -> bookController.updateBook(bookToRegister, bookInDatabaseToChange.getISBN(),authorizationString));
        }
    }
    @Nested
    @DisplayName("Testing access story 10C delete a book as librarian only")
    class testDeleteAbookAsLibrarian {
        @Test
        @DisplayName("When asked to delete a new book as a member, an exception should be thrown")
        void whenAskedToRegisterANewBookAsMember_shouldThrowException() {
            //given
            Member member = new Member("12315615", "BookController1", "Sven", "deleteABookAsAMemberTest@mail.be", "1591", "teststraat", 156, 156, "Brussel");
            userRepository.addMember(member);

            Book bookInDatabaseToChange = new Book("789789789789", "efef", new Author("efz", "zefzf"));
            bookRepository.addBook(bookInDatabaseToChange);

            String correctEmail = "deleteABookAsAMemberTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertThrows(IncorrectLogInInformationException.class, () -> bookController.deleteBookByISBN(authorizationString,bookInDatabaseToChange.getISBN()));
        }

        @Test
        @DisplayName("When asked to update a book as a librarian, an exception should not be thrown")
        void whenAskedToRegisterABookAsALibrarian_shouldThrowException() {
            //given
            User librarian = new User("12315615", "BookController1", "Sven", "deleteBookAsALibrarianTest@mail.be", Role.LIBRARIAN);
            userRepository.addUser(librarian);

            Book bookInDatabaseToChange = new Book("789sdf789sf789sdf789", "efef", new Author("efz", "zefzf"));
            bookRepository.addBook(bookInDatabaseToChange);

            String correctEmail = "deleteBookAsALibrarianTest@mail.be:12315615";
            byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
            String authorizationString = "Basic " + new String(inputEncoded);

            //when
            Assertions.assertDoesNotThrow(() -> bookController.deleteBookByISBN(authorizationString,bookInDatabaseToChange.getISBN()));
        }
    }
}