package ua.com.abakumov.kafkaswing;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class ConnectionSettings {
    public enum Type {
        String, Label, Instant, Boolean
    }

    public class UIModel {
        private final Type type;
        private final String label;
        private final Optional<Consumer> updater;
        private final Supplier accessor;

        public UIModel(Type type, String label, Optional<Consumer> updater, Supplier accessor) {
            this.type = type;
            this.label = label;
            this.updater = updater;
            this.accessor = accessor;
        }

        public Type getType() {
            return type;
        }

        public String getLabel() {
            return label;
        }

        public Optional<Consumer> getUpdater() {
            return updater;
        }

        public Supplier getAccessor() {
            return accessor;
        }
    }
    private String id;
    private String description;
    private String note;
    private Instant created;
    private Instant lastUpdated;
    private boolean activated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<UIModel> getUIModel() {
        var model = new LinkedHashSet<UIModel>();
        model.add(new UIModel(Type.Label, "Id", Optional.empty(), this::getId));
        model.add(new UIModel(Type.String, "Description", Optional.of((v) -> this.setDescription(v.toString())), this::getDescription));
        model.add(new UIModel(Type.String, "Note", Optional.of((v) -> this.setNote(v.toString())), this::getNote));
        model.add(new UIModel(Type.Instant, "Created", Optional.empty(), this::getCreated));
        model.add(new UIModel(Type.Instant, "Updated", Optional.of((v) -> this.setLastUpdated((Instant) v)), this::getLastUpdated));
        model.add(new UIModel(Type.Boolean, "Activated", Optional.of((v) -> this.setActivated((Boolean) v)), this::isActivated));

        return model;
    }

    public final Set<UIModel> concatenate(Set<UIModel> uiModel) {
        var model = getUIModel();
        model.addAll(uiModel);
        return model;
    }
}
