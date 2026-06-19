package com.denisar5.perfumehub.init;

import com.denisar5.perfumehub.model.entity.Perfume;
import com.denisar5.perfumehub.model.enums.Gender;
import com.denisar5.perfumehub.repository.PerfumeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PerfumeRepository perfumeRepository;

    public DataInitializer(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public void run(String... args) {

        if (perfumeRepository.count() > 0) {
            return;
        }

        perfumeRepository.save(
                Perfume.builder()
                        .name("Sauvage")
                        .brand("Dior")
                        .description("Fresh and powerful fragrance.")
                        .price(new BigDecimal("129.99"))
                        .imageUrl("https://cdn.notinoimg.com/detail_main_mq/dior/3348901486385_01/sauvage___200828.jpg")
                        .gender(Gender.MALE)
                        .volumeMl(100)
                        .stockQuantity(50)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        perfumeRepository.save(
                Perfume.builder()
                        .name("Le Male Le Parfum")
                        .brand("Jean Paul Gautltier")
                        .description("Elegant woody aromatic fragrance.")
                        .price(new BigDecimal("139.99"))
                        .imageUrl("https://cdn.notinoimg.com/detail_main_mq/jean-paul-gaultier/8435415032315_01-o/le-male-le-parfum___250411.jpg")
                        .gender(Gender.MALE)
                        .volumeMl(120)
                        .stockQuantity(40)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        perfumeRepository.save(
                Perfume.builder()
                        .name("Eclaire")
                        .brand("Lattafa")
                        .description("Floral fragrance with lavender and orange blossom.")
                        .price(new BigDecimal("109.99"))
                        .imageUrl("https://cdn.notinoimg.com/detail_main_mq/lattafa/6290362340638_01-o/eclaire___251125.jpg")
                        .gender(Gender.FEMALE)
                        .volumeMl(90)
                        .stockQuantity(35)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        perfumeRepository.save(
                Perfume.builder()
                        .name("Private Blend Tobacco Vanille")
                        .brand("Tom Ford")
                        .description("Luxury oud and sandalwood fragrance.")
                        .price(new BigDecimal("249.99"))
                        .imageUrl("https://cdn.notinoimg.com/detail_main_mq/tom-ford/888066000512_01-o/private-blend-tobacco-vanille___250310.jpg")
                        .gender(Gender.UNISEX)
                        .volumeMl(50)
                        .stockQuantity(20)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        perfumeRepository.save(
                Perfume.builder()
                        .name("Aventus for Her")
                        .brand("Creed")
                        .description("Luxury fruity and smoky fragrance.")
                        .price(new BigDecimal("319.99"))
                        .imageUrl("https://cdn.notinoimg.com/detail_main_mq/creed/3508444404424_01/aventus-for-her___260210.jpg")
                        .gender(Gender.FEMALE)
                        .volumeMl(130)
                        .stockQuantity(15)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        System.out.println("Perfume seed data inserted.");
    }
}
