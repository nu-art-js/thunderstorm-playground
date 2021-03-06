@Library('dev-tools@pipeline')

import com.nu.art.pipeline.modules.SlackModule
import com.nu.art.pipeline.workflow.Pipeline_BaseProxy
import com.nu.art.pipeline.workflow.Workflow

class Pipeline_ThunderstormProxy
	extends Pipeline_BaseProxy<Pipeline_ThunderstormProxy> {

	Pipeline_ThunderstormProxy() {
		super("proxy")
	}

	@Override
	protected void init() {
		declareJob("dev", "${JENKINS_JOB_NAME}--DEV")
		declareJob("staging", "${JENKINS_JOB_NAME}--STAGING")
		declareJob("prod", "${JENKINS_JOB_NAME}--PROD")

		getModule(SlackModule.class).disable()

		super.init()
	}
}


node() {
	Workflow.createWorkflow(Pipeline_ThunderstormProxy.class, this)
}

