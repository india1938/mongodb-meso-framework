package framework.mesos.mongodb;
import org.apache.log4j.Logger;
import org.apache.mesos.Protos;
import org.apache.mesos.SchedulerDriver;

import state.FrameworkState;

public class FrameworkInfoFactory {
	private static final Logger LOGGER = Logger.getLogger(FrameworkInfoFactory.class);
	private final Configuration configuration;
	private FrameworkState frameworkState;

	public FrameworkInfoFactory(Configuration configuration, FrameworkState frameworkState) {
		this.configuration = configuration;
		this.frameworkState = frameworkState;
	}

	public Protos.FrameworkInfo.Builder getBuilder() {
		final Protos.FrameworkInfo.Builder frameworkBuilder = Protos.FrameworkInfo.newBuilder();
		frameworkBuilder.setUser("");
		frameworkBuilder.setName(configuration.getFrameworkName());
		frameworkBuilder.setFailoverTimeout(configuration.getFailoverTimeout());
		frameworkBuilder.setCheckpoint(true); 
		frameworkBuilder.setRole(configuration.getframeworkRole());
		setFrameworkId(frameworkBuilder);
		return frameworkBuilder;
	}



	private void setFrameworkId(Protos.FrameworkInfo.Builder frameworkBuilder) {
		Protos.FrameworkID frameworkID = frameworkState.getFrameworkID();
		if (frameworkID != null && !frameworkID.getValue().isEmpty()) {
			LOGGER.info("Found previous frameworkID: " + frameworkID);
			frameworkBuilder.setId(frameworkID);
		}


	}

}
