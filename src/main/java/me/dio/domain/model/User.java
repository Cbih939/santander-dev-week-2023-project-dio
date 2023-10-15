package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    private Card card;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Feature> features;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<News> news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    // Atualizar usuario
    public void updateUserData(String newName, Account newAccount, Card newCard,
                               List<Feature> newFeatures, List<News> newNews) {
        if (newName != null && !newName.isEmpty()) {
            this.name = newName;
        }
        if (newAccount != null) {
            this.account = newAccount;
        }
        if (newCard != null) {
            this.card = newCard;
        }
        if (newFeatures != null) {
            this.features = newFeatures;
        }
        if (newNews != null) {
            this.news = newNews;
        }
    }

    // Deletar usuario
    public void deleteUser(EntityManager entityManager) {
        if (entityManager != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();

                // Remova o usuário do banco de dados
                entityManager.remove(this);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace(); // Trate ou lance a exceção conforme necessário
            }
        }
    }
}

