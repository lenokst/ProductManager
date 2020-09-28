package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    Product first = new Book(1, "Просто Маса", 1, "Борис Акунин");
    Product second = new Book(2, "Думай медленно… Решай быстро", 2, "Даниэль Канеман");
    Product third = new Book(3, "Deadline. Роман об управлении проектами", 3, "Том ДеМарко");
    Product fourth = new Smartphone(4, "Galaxy S20 Ultra", 4, "Samsung");
    Product fifth = new Smartphone(5, "P40 Pro", 5, "Huawei");
    Product sixth = new Smartphone(6, "Mi 10", 6, "Xiaomi");

    @BeforeEach
    void setUp() {
        manager.addProduct(first);
        manager.addProduct(second);
        manager.addProduct(third);
        manager.addProduct(fourth);
        manager.addProduct(fifth);
        manager.addProduct(sixth);
    }

    @Test
    void searchByBookAuthor() {
        Product[] actual = manager.searchBy("Даниэль Канеман");
        Product[] expected = {new Book(2, "Думай медленно… Решай быстро", 2, "Даниэль Канеман")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookName() {
        Product[] actual = manager.searchBy("Просто Маса");
        Product[] expected = {new Book(1, "Просто Маса", 1, "Борис Акунин")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByInvalidAuthor() {
        Product[] actual = manager.searchBy("Рольф Добелли");
        Product[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneManufacturer() {
        Product[] actual = manager.searchBy("Samsung");
        Product[] expected = {new Smartphone(4, "Galaxy S20 Ultra", 4, "Samsung")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneName() {
        Product[] actual = manager.searchBy("P40 Pro");
        Product[] expected = {new Smartphone(5, "P40 Pro", 5, "Huawei")};
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByInvalidManufacturer() {
        Product[] actual = manager.searchBy("Nokia");
        Product[] expected = {};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{second, third, fourth, fifth, sixth};
        assertArrayEquals(expected, actual);
    }
}