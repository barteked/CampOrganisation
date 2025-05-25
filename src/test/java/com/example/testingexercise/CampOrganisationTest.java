package com.example.testingexercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CampOrganisationTest {

    @Test
    public void shouldThrowExceptionWhenNullFirstName() {
        //given
        var campOrganisation = new CampOrganisation(new ArrayList<>());
        //when //then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> campOrganisation.addStudent(null, "Last")
        );//sprwadza czy jest null to zabezpiecznie przed dodawaniem nieprawidlowych danych, nie logicznych!
    }

    @Test
    public void shouldThrowExceptionWhenNullLastName() {
        //given
        var campOrganisation = new CampOrganisation(new ArrayList<>());
        //when //then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> campOrganisation.addStudent("First", null)
        );// tak samo jak wyzej to dziala!
    }

    @Test
    public void shouldReturnTransformedName() {
        //given
        var campOrganisation = new CampOrganisation(new ArrayList<>());
        //when
        String actual = campOrganisation.transformName("First", "Last");
        //then
        Assertions.assertEquals("First Last", actual);
    }// sprwadza czy dobrze laczy imie i naziwsko w jedna fraze wazne dlatego bo to podstawowa logika z ktorej korzysta addSTudnet!

    @Test
    public void shouldReturnFalseWhenNameAlreadyInTheList() {
        //given
        var campOrganisation = new CampOrganisation(List.of("First Last"));
        //when
        boolean actual = campOrganisation.addStudent("First", "Last");
        //then
        Assertions.assertFalse(actual);
    }//jak jest juz w liscie to zwraca false! zapobiega dodawaniu dupliatkow!

    @Test
    public void shouldReturnTrueWhenAddingNewStudent() {
        //given
        var campOrganisation = new CampOrganisation(new ArrayList<>());
        Assertions.assertEquals("", campOrganisation.getStudent("First"));
        //when
        boolean actual = campOrganisation.addStudent("First", "Last");
        //then
        Assertions.assertTrue(actual);
        Assertions.assertEquals("First Last", campOrganisation.getStudent("First"));
    }// przed dodaniem sprawdza ze student nie istneije, dodaje go, sprawdza czy jest dodany, i zwraca true czyli udalo sie dodac!

    @Test
    public void shouldReturnStudentByPartialMatch() {
        //given
        var campOrganisation = new CampOrganisation(List.of("First Last"));
        //when
        String result = campOrganisation.getStudent("First");
        //then
        Assertions.assertEquals("First Last", result);
    }//wymagana logika z ktorej korzysta getStudent! a nie wymagala dokladnego dopasowania!

    @Test
    public void shouldReturnEmptyStringWhenStudentNotFound() {
        //given
        var campOrganisation = new CampOrganisation(new ArrayList<>());
        //when
        String result = campOrganisation.getStudent("Missing");
        //then
        Assertions.assertEquals("", result);
    }//jesli szukamy studenta ktory nie istnieje to zwraca pusty string!
    //to bezpiecznejsze domyslne zwracanie pustego stringa zamiast null!
}