import sample.SampleDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

public class EmbeddedDatabaseTest {

    EmbeddedDatabase db;
    SampleDAO sampleDAO;

    @Before
    public void setUp(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        db = builder.setType(H2).addScripts("schema.sql", "test-data.sql").build();
        sampleDAO = new SampleDAO(new JdbcTemplate(db));
    }

    @Test
    public void queryMembers(){
        List<String> foundMembers = sampleDAO.findMemberNames();
        assertEquals(3, foundMembers.size());
        assertTrue(foundMembers.contains("Codr, Jessica"));
        assertTrue(foundMembers.contains("Cafe, Babe"));
        assertTrue(foundMembers.contains("Oracle, Sun"));
    }

    @Test
    public void queryMembersAttendingMeeting(){
        List<String> foundMembers = sampleDAO.findMembersAttendingMeetingOn("2018-12-01");
        assertEquals(2, foundMembers.size());
        for(String member : foundMembers){
            System.out.println(member);
        }
        assertTrue(foundMembers.contains("Codr, Jessica"));
        assertTrue(foundMembers.contains("Cafe, Babe"));
    }

    @Test
    public void queryMembersSpeakingAtMeeting(){
        List<String> foundMembers = sampleDAO.findMembersSpeakingAtMeetingOn("2019-01-15");
        assertEquals(2, foundMembers.size());
        for(String member : foundMembers){
            System.out.println(member);
        }
        assertTrue(foundMembers.contains("Codr, Jessica"));
        assertTrue(foundMembers.contains("Oracle, Sun"));
    }

    @After
    public void tearDown(){
        db.shutdown();
    }

}
