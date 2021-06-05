public class AlreadyExistingEntityException extends ProjectExceptions {

    public AlreadyExistingEntityException() {
    }

    public void getExceptionInfo() {
        System.out.println("This entity is already catalogued in our system.");
    }
}