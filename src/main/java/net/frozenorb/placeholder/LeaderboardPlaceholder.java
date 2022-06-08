package net.frozenorb.placeholder;

import net.frozenorb.data.GameType;
import org.apache.commons.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

// StrSubstitutor Is Deprecated, Will Fix This Later
// to do list for this class
// TODO: Make it work, make it better, make it like function
@SuppressWarnings({"deprecation", "unused"})
public class LeaderboardPlaceholder {
    public String fillIn(String raw, GameType type) {
        if(type == GameType.PRACTICE) {
            Map<String, String> data = new HashMap<>();
            data.put("global.1", "");

            return StrSubstitutor.replace(raw, data);
        }
        return raw;
    }
}
