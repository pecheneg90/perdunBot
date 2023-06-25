package project.bot.perdunbot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "bot_table")
@Setter
@Getter
@NoArgsConstructor
public class BotEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chatId;
    @Lob
    private byte[] image;

    @Override
    public String toString() {
        return "BotEntities{" +
                "chatId=" + chatId +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotEntities that = (BotEntities) o;
        return chatId == that.chatId && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(chatId);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}