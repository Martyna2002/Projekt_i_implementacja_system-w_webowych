package com.kino.kino.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bilet {
    @Id
    private String kod;

    @ManyToOne
    private Seans seans;

    private String miejsca;
    private LocalDateTime dataRezerwacji;
    private String status;

    // Gettery i settery
    public String getKod() { return kod; }
    public void setKod(String kod) { this.kod = kod; }

    public Seans getSeans() { return seans; }
    public void setSeans(Seans seans) { this.seans = seans; }

    public String getMiejsca() { return miejsca; }
    public void setMiejsca(String miejsca) { this.miejsca = miejsca; }

    public LocalDateTime getDataRezerwacji() { return dataRezerwacji; }
    public void setDataRezerwacji(LocalDateTime dataRezerwacji) { this.dataRezerwacji = dataRezerwacji; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
