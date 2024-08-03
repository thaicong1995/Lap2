package Lap2.example.Lap2.Common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagingModel {
    public int page = 1;
    public int pageSize = 10;
    public int pageCount;
}
