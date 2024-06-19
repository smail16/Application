package com.soprasteria.shared.collection.domain;

import com.soprasteria.UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@UnitTest
class OscaCollectionsTest {

    @Nested
    @DisplayName("Collections")
    class OscaCollectionsCollectionsTest {

        @Test
        void shouldGetEmptyImmutableCollectionFromNullCollection() {
            Collection<Object> input = null;
            Collection<Object> collection = OscaCollections.immutable(input);

            assertThat(collection).isEmpty();
            assertThatThrownBy(collection::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableCollectionFromMutableCollection() {
            Collection<String> input = new ArrayList<>();
            input.add("value");
            Collection<String> collection = OscaCollections.immutable(input);

            assertThat(collection).containsExactly("value");
            assertThatThrownBy(collection::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("Set")
    class OscaCollectionsSetTest {

        @Test
        void shouldGetEmptyImmutableCollectionFromNullCollection() {
            Set<Object> input = null;
            Set<Object> set = OscaCollections.immutable(input);

            assertThat(set).isEmpty();
            assertThatThrownBy(set::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableCollectionFromMutableCollection() {
            Set<String> input = new HashSet<>();
            input.add("value");
            Set<String> set = OscaCollections.immutable(input);

            assertThat(set).containsExactly("value");
            assertThatThrownBy(set::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("List")
    class OscaCollectionsListTest {

        @Test
        void shouldGetEmptyImmutableCollectionFromNullCollection() {
            List<Object> input = null;
            List<Object> list = OscaCollections.immutable(input);

            assertThat(list).isEmpty();
            assertThatThrownBy(list::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableCollectionFromMutableCollection() {
            List<String> input = new ArrayList<>();
            input.add("value");
            List<String> list = OscaCollections.immutable(input);

            assertThat(list).containsExactly("value");
            assertThatThrownBy(list::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }

    @Nested
    @DisplayName("Map")
    class OscaMapTest {

        @Test
        void shouldGetEmptyImmutableMapFromNullMap() {
            Map<Object, Object> input = null;
            Map<Object, Object> map = OscaCollections.immutable(input);

            assertThat(map).isEmpty();
            assertThatThrownBy(map::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        void shouldGetImmutableMapFromMutableMap() {
            Map<String, String> input = new HashMap<>();
            input.put("key", "value");
            Map<String, String> map = OscaCollections.immutable(input);

            assertThat(map).containsExactly(Map.entry("key", "value"));
            assertThatThrownBy(map::clear).isExactlyInstanceOf(UnsupportedOperationException.class);
        }
    }
}
