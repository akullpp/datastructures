package de.akull.datastructures.stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StackTest {

    private Stack stack;
    private Node n1 = new Node<>(1);
    private Node n2 = new Node<>(2);
    private Node n3 = new Node<>(3);

    @BeforeEach
    void init() {
        stack = new Stack();
        stack.push(n1);
        stack.push(n2);
        stack.push(n3);
    }

    @Test
    void Should_Remove_All_Nodes() {
        assertThat(stack.isEmpty()).isFalse();

        stack.pop();
        stack.pop();
        stack.pop();

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void Should_Throw_When_No_Nodes_And_Remove_Is_Called() {
        stack.pop();
        stack.pop();
        stack.pop();

        assertThatThrownBy(() -> stack.pop()).isInstanceOf(EmptyStackException.class);
    }

    @Test
    void Should_Retrieve_Head_Without_Removing() {
        assertThat(stack.peek()).isEqualTo(n3);
        assertThat(stack.head).isEqualTo(n3);
    }
}
