package ru.spring.hibernate.model;



import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/*В базе данных необходимо реализовать возможность хранить информацию о покупателях (id, имя)
и товарах (id, название, стоимость). У каждого покупателя свой набор купленных товаров;

Для обеих сущностей создаете Dao классы. Работу с SessionFactory выносите во вспомогательный класс;

* Создаете сервис, позволяющий по id покупателя узнать список купленных им товаров, и по id товара узнавать
список покупателей этого товара;

** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;

ВАЖНО И ОБЯЗАТЕЛЬНО! Dao классы и сервис должны являться Spring бинами (Вам нужен Spring Context без веб части).
Контроллеры создавать не надо.

ВАЖНО! Выкидываете код по подготовке данных и таблиц, и делаете отдельный скрипт и формируете базу заранее.
Покупателей и товары в базу складываете заранее, через код этого делать не надо (лишнее усложнение).
SQL-скрипт прикрепите к работе.
*/
@NoArgsConstructor
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, price = %d]", id, title, cost);
    }

    @ManyToMany
    @JoinTable(
            name = "sale",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_users")
    )
    private List<Users> usersSale;

    public List<Users> getUsers() {
        return usersSale;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
