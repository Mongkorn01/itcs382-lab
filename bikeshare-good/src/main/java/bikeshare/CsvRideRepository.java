package bikeshare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CsvRideRepository implements RideRepository {
    private final Path file;

    public CsvRideRepository(Path file) {
        this.file = file;
    }

    @Override
    public void save(Ride ride) throws IOException {
        boolean needsHeader =
                !Files.exists(file) || Files.size(file) == 0;

        try (var writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            if (needsHeader) {
                writer.write("memberId,memberType,bikeId,startStation,"
                        + "returnStation,minutes,fee");
                writer.newLine();
            }

            writer.write(String.join(",",
                    csv(ride.getMemberId()),
                    ride.getMemberType().name(),
                    csv(ride.getBike().getId()),
                    csv(ride.getStartStation().toString()),
                    csv(ride.getReturnStation().toString()),
                    String.valueOf(ride.getMinutes()),
                    String.valueOf(ride.getFee())));
            writer.newLine();
        }
    }

    private String csv(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
}
