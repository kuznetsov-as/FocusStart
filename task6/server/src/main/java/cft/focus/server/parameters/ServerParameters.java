package cft.focus.server.parameters;

import cft.focus.server.exceptions.ServerParametersException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;


@Slf4j
public class ServerParameters {
    private final Properties properties = new Properties();

    @Getter
    private final int port;

    public ServerParameters() throws ServerParametersException {
        initializeProperties();
        this.port = getPortFromFile();
    }

    /**
     * Загрузка данных из .properties-файла
     */
    private void initializeProperties() throws ServerParametersException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(
                "properties/Server.properties")).getFile());
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new ServerParametersException("Не удалось найти файл " +
                    "\"Server.properties\"");
        } catch (IOException e) {
            throw new ServerParametersException("Не удалось получить данные из файла " +
                    "\"Server.properties\"");
        }
    }

    /**
     * Получение номера порта
     *
     * @return Номер порта
     * @throws ServerParametersException Возникает если номер порта находится за пределами
     *                                   валидных значений (0 и 65535), или был обнаружен
     *                                   неверный формат данных
     */
    private int getPortFromFile() throws ServerParametersException {
        try {
            int port = Integer.parseInt(properties.getProperty("port"));

            if (port < 0) {
                log.info("В файле \"Server.properties\" " +
                        "для ключа \"port\" обнаружено значение меньше 0");
                throw new ServerParametersException("Не удалось получить порт");
            } else if (port > 65535) {
                log.info("В файле \"Server.properties\" " +
                        "для ключа \"port\" обнаружено значение больше 65535");
                throw new ServerParametersException("Не удалось получить порт");
            }

            return port;

        } catch (NumberFormatException e) {
            log.info("В файле \"Server.properties\" обнаружен неверный формат " +
                    "данных для ключа \"port\"");
            throw new ServerParametersException("Не удалось получить порт");
        }
    }
}