package fr.olprog_c.le_phare_culturel.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;


@Entity
@Table(name = "events_groups_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @DynamicUpdate
public class EventGroupUserEntity extends BaseCommonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "group_name", nullable = false, length = 50)
	private String groupName;

	@Column(name = "group_size", nullable = false, columnDefinition = "TINYINT UNSIGNED")
	private Integer groupMaxSize;

	@Temporal(TemporalType.TIMESTAMP)
	private String timeMeet;

	@Column(name = "description", nullable = false, length = 255)
	private String description;

	@ManyToOne(optional = false)
	private UserEntity referencedUserAuthor;

	@ManyToOne()
	@JoinColumn(name = "related_events")
	private EventEntity relatedEvents;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<UserEntity> referencedUserList;

	@OneToMany(mappedBy = "relatedEventsGroups", orphanRemoval = true)
	private Collection<EventGroupUserMessageEntity> referencedGroupsMessages;

}
