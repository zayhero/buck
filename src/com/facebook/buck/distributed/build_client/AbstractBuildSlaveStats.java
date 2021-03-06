/*
 * Copyright 2017-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.distributed.build_client;

import com.facebook.buck.core.util.immutables.BuckStyleImmutable;
import com.facebook.buck.distributed.thrift.BuildSlaveFinishedStats;
import com.facebook.buck.distributed.thrift.BuildSlaveRunId;
import com.facebook.buck.distributed.thrift.StampedeId;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import org.immutables.value.Value;
import org.immutables.value.Value.Parameter;

/** Contains stats of all BuildSlaves that took part in a distributed build. */
@Value.Immutable
@BuckStyleImmutable
abstract class AbstractBuildSlaveStats {

  @Parameter
  public abstract StampedeId getStampedeId();

  public abstract ImmutableMap<BuildSlaveRunId, Optional<BuildSlaveFinishedStats>>
      getBuildSlaveStats();

  public Optional<BuildSlaveFinishedStats> getStatsForBuildSlave(BuildSlaveRunId runId) {
    return Preconditions.checkNotNull(getBuildSlaveStats().get(runId));
  }
}
