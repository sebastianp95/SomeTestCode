public interface CacheManager {
    // returns the person identified by the input parameter phone number
    public Person getPerson(int phoneNum);

    // returns the phone number of the added person
    int addPerson(Person person);
}
