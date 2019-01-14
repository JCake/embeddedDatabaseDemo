package sample;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SampleDAO {

    public static final String MEMBERS_AT_MEETING_SQL = "select last_name, first_name from OJUG_MEMB m " +
            "join OJUG_MEET_ATTEND a on a.member_id = m.member_id " +
            "join OJUG_MEETINGS t on t.meeting_id = a.meeting_id where t.meeting_date = ?";
    private JdbcTemplate jdbcTemplate;

    public SampleDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findMemberNames() {
        // Feature not supported:
        //return this.jdbcTemplate.queryForList("select last_name + ',' + first_name as person from OJUG_MEMB", String.class);
        return this.jdbcTemplate.queryForList("select last_name, first_name from OJUG_MEMB")
                .stream().map((entry) -> entry.get("last_name") + ", " + entry.get("first_name"))
                .collect(Collectors.toList());
    }

    public List<String> findMembersAttendingMeetingOn(String date) {
        return this.jdbcTemplate.queryForList(MEMBERS_AT_MEETING_SQL, date)
                .stream().map((entry) -> entry.get("last_name") + ", " + entry.get("first_name"))
                .collect(Collectors.toList());
    }

    public List<String> findMembersSpeakingAtMeetingOn(String date) {
        return this.jdbcTemplate.queryForList(MEMBERS_AT_MEETING_SQL + " and a.presented = 'Y'", date)
                .stream().map((entry) -> entry.get("last_name") + ", " + entry.get("first_name"))
                .collect(Collectors.toList());
    }
}
