package TechTigers.BicycleBuddies.models;

import jakarta.persistence.Entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Config extends AbstractEntity{

    @NotNull
    @Size(min = 2, max = 150, message = "Invalid Key.  Must be between 2 and 150 characters.")
    private String ConfigKey;

    @NotNull
    @Size(min = 2, max = 150, message = "Invalid value.  Must be between 2 and 150 characters.")
    private String ConfigValue;

    public Config() {
    }

    public Config(String configKey, String configValue) {
        ConfigKey = configKey;
        ConfigValue = configValue;
    }

    public @NotNull @Size(min = 2, max = 150, message = "Invalid Key.  Must be between 2 and 150 characters.") String getConfigKey() {
        return ConfigKey;
    }

    public void setConfigKey(@NotNull @Size(min = 2, max = 150, message = "Invalid Key.  Must be between 2 and 150 characters.") String configKey) {
        ConfigKey = configKey;
    }

    public @NotNull @Size(min = 2, max = 150, message = "Invalid value.  Must be between 2 and 150 characters.") String getConfigValue() {
        return ConfigValue;
    }

    public void setConfigValue(@NotNull @Size(min = 2, max = 150, message = "Invalid value.  Must be between 2 and 150 characters.") String configValue) {
        ConfigValue = configValue;
    }
}