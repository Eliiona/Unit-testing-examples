package lv.course.testing.frameworks.service.account;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class Examples {

    @Test
    void simpleMockExample() {
        // Lets create a mock of a List
        ArrayList listMock = mock(ArrayList.class);

        // Then we can stub some behaviours with specific arguments
        when(listMock.get(0)).thenReturn("first");
        when(listMock.get(1)).thenThrow(new RuntimeException("second"));

        // This will print "first"
        System.out.println(listMock.get(0));

        // This will throw RuntimeException with message "second"
        System.out.println(listMock.get(1));

        // This will print null, as we have not defined the return behaviour for this argument.
        System.out.println(listMock.get(2));
    }

    @Test
    void testService() {
        // Create mocks on classes our service depends on
        DelegateOne mockOne = mock(DelegateOne.class);
        DelegateTwo mockTwo = mock(DelegateTwo.class);
        ConditionResolver mockResolver = mock(ConditionResolver.class);
        // Create service instance using our mocks
        Service service = new Service(mockOne, mockTwo, mockResolver);
        // Stub behaviour, so that condition in if is 'true'
        when(mockResolver.shouldDoMore(33)).thenReturn(true);
        // Call our service method
        service.doAllThings(33);
        // Verify that methods we expect are called with given arguments
        verify(mockOne).doSomething(33);
        verify(mockTwo).doSomething(33);
    }

    private class Service {

        private final DelegateOne delegateOne;
        private final DelegateTwo delegateTwo;
        private final ConditionResolver resolver;

        public Service(DelegateOne delegateOne, DelegateTwo delegateTwo, ConditionResolver resolver) {
            this.delegateOne = delegateOne;
            this.delegateTwo = delegateTwo;
            this.resolver = resolver;
        }

        public void doAllThings(int value) {
            delegateOne.doSomething(value);
            if (resolver.shouldDoMore(value)) {
                delegateTwo.doSomething(value);
            }
        }
    }

    private class DelegateOne {

        public void doSomething(int number) {
            // Does Delegate one things
        }
    }

    private class DelegateTwo {

        public void doSomething(int number) {
            // Does Delegate two things
        }

    }

    private class ConditionResolver {

        public boolean shouldDoMore(int number) {
            // Checks if delegate two should be called
            return false;
        }
    }
 }
